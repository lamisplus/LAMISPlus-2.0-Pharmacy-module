import React, {useEffect, useState} from 'react';
import MaterialTable from 'material-table';

import { Link } from 'react-router-dom'
import VisibilityIcon from '@material-ui/icons/Visibility';
import Tooltip from '@material-ui/core/Tooltip';
import IconButton from '@material-ui/core/IconButton';
 import axios from "axios";
import { url as baseUrl, token as token  } from "../../../api";
import {PrescriptionObj} from './PrescriptionObj';
import SplitActionButton from '../../layouts/SplitActionButton';
import {FaEye, FaUserPlus} from "react-icons/fa";

import { forwardRef } from 'react';

import AddBox from '@material-ui/icons/AddBox';
import ArrowUpward from '@material-ui/icons/ArrowUpward';
import Check from '@material-ui/icons/Check';
import ChevronLeft from '@material-ui/icons/ChevronLeft';
import ChevronRight from '@material-ui/icons/ChevronRight';
import Clear from '@material-ui/icons/Clear';
import DeleteOutline from '@material-ui/icons/DeleteOutline';
import Edit from '@material-ui/icons/Edit';
import FilterList from '@material-ui/icons/FilterList';
import FirstPage from '@material-ui/icons/FirstPage';
import LastPage from '@material-ui/icons/LastPage';
import Remove from '@material-ui/icons/Remove';
import SaveAlt from '@material-ui/icons/SaveAlt';
import Search from '@material-ui/icons/Search';
import ViewColumn from '@material-ui/icons/ViewColumn';

const tableIcons = {
Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
DetailPanel: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
PreviousPage: forwardRef((props, ref) => <ChevronLeft {...props} ref={ref} />),
ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
SortArrow: forwardRef((props, ref) => <ArrowUpward {...props} ref={ref} />),
ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />)
};

const PatientSearch = (props) => {
  const prescriptions = PrescriptionObj;
  const [prescriptionList, setPrescriptionList] = useState([])
    useEffect(() => {
        Prescribtions();
    }, []);
    
    ///GET LIST OF Drug Prescriptions
    async function Prescribtions() {
        axios
            .get(`${baseUrl}drug-orders/patients`,{ headers: {"Authorization" : `Bearer ${token}`} })
            .then((response) => {
                console.log("pres", response)
                setPrescriptionList(response.data);
            })
            .catch((error) => {
            });
    }
    //Get total drug dispensed 
    const totalDrugsPrescribed = (drugsArray) => {
      const dispensed = []
        drugsArray.map(drugs => {
            if (drugs.status === 1)
              dispensed.push(drugs)
        })
    return dispensed.length
  }
    const drugType = (drugsArray) => {
    //console.log(prescriptions)
    const type = []
    drugsArray.map(drugs => {
        if (drugs.data.type === 1){
          type.push('Drug')
        }else if(drugs.data.type === 0){
          type.push('Regimen')
        }else{
          type.push('')
        }
       
    })
    
    return type
  }

    const actionItems = prescription => {
              return  [
                  {
                      name:'View',
                      type:'link',
                      icon:<FaEye  size="22"/>,
                      to:{
                          pathname: "/prescriptions",
                          state: prescription
                      }
                  },

    //              {...(permissions.includes('view_patient') || permissions.includes("all_permission")&&
    //                      {
    //                          name:'Print Manifest',
    //                          type:'link',
    //                          icon:<MdPerson size="20" color='rgb(4, 196, 217)' />,
    //                          to:{
    //                              pathname: "/print-manifest",
    //                              state: { sampleObj: row, permissions:permissions  }
    //                          }
    //                      }
    //              )},
               ]
           }
 
  return (
    <div>
      <MaterialTable
       icons={tableIcons}
        title="Drug Prescriptions"
        columns={[
          { title: "Patient ID", field: "Id" },
          {
            title: "Patient Name",
            field: "name",
          },
          { title: "Prescription Date", field: "date", type: "date", filtering: false, },
          {
            title: "Total Prescribed",
            field: "prescribedCount",
            filtering: false,
          },
          {
            title: "Total Dispensed",
            field: "dispensedCount",
            filtering: false,
          },
          {
            title: "Action",
            field: "actions",
            filtering: false,
          },
        ]}
        data={ prescriptionList.map((prescription) => ({
          Id: prescription.patientHospitalNumber,
          name: prescription.patientFirstName + " " + prescription.patientLastName,
          date: prescription.drugOrders[0].dateTimePrescribed.replace("@"," "),
          prescribedCount: prescription.drugOrders.length,
          dispensedCount: totalDrugsPrescribed(prescription.drugOrders),
         // type:   drugType(prescription.formDataObj),
          actions: (
                <div>
                   <SplitActionButton actions={actionItems(prescription)} />
                </div>
          ),
        }))}
                  options={{
                    headerStyle: {
                        backgroundColor: "#014d88",
                        color: "#fff"
                    },
                    searchFieldStyle: {
                        width : '300%',
                        margingLeft: '250px',
                    },
                    filtering: false,
                    exportButton: false,
                    searchFieldAlignment: 'left',
                    pageSizeOptions:[10,20,100],
                    pageSize:10,
                    debounceInterval: 400
                }}
      />
    </div>
  );
}

export default PatientSearch;



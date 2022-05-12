import React, {useState, useEffect} from 'react';
import {  Modal, ModalHeader, ModalBody,
    Form,
    Row,
    Col,Input,
    FormGroup,FormFeedback,
    Label,Card, CardBody
} from 'reactstrap';

import MatButton from '@material-ui/core/Button'
import { makeStyles } from '@material-ui/core/styles'
import SaveIcon from '@material-ui/icons/Save'
import CancelIcon from '@material-ui/icons/Cancel'
import Chip from '@material-ui/core/Chip';
import TextField from '@material-ui/core/TextField';
import Autocomplete from '@material-ui/lab/Autocomplete';
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "react-widgets/dist/css/react-widgets.css";
import { DateTimePicker } from 'react-widgets';
import Moment from 'moment';
import momentLocalizer from 'react-widgets-moment';
import moment from "moment";
import { Spinner } from 'reactstrap';
// import { useSelector, useDispatch } from 'react-redux';
// import './modal.css';
import { url } from "../../../api";
import axios from "axios";


Moment.locale('en');
momentLocalizer();
const useStyles = makeStyles(theme => ({
    card: {
        margin: theme.spacing(20),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center'
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(3)
    },
    submit: {
        margin: theme.spacing(3, 0, 2)
    },
    cardBottom: {
        marginBottom: 20
    },
    Select: {
        height: 45,
        width: 350
    },
    button: {
        margin: theme.spacing(1)
    },

    root: {
        '& > *': {
            margin: theme.spacing(1)
        }
    },
    input: {
        display: 'none'
    }
}))

const DispenseModal = (props) => {
    const { buttonLabel, className } = props;
    const [errors, setErrors] = useState({});
    const toggle = props.togglestatus
    const modal = props.modalstatus
    const [loading, setLoading] = useState(false)
    const closeBtn = props.close
    const classes = useStyles();
    const [optionsample, setOptionsample] = useState([]);
    const formData = props.formData ? props.formData : {}
    const [formValues, setFormValues] = useState({})
    const [samples, setSamples] = useState({});
    const [otherfields, setOtherFields] = useState({sample_collected_by:"",sample_ordered_by:"",sample_priority:"",time_sample_collected:"", comment_sample_collected:""});
    console.log(props.formData)
    const handleInputChange = (e) => {
        setFormValues ({ ...formValues, [e.target.name]: e.target.value });
    }

    // useEffect(() => {
    //     async function getCharacters() {
    //         try {
    //             const response = await axios(
    //                 url + "drugs"
    //             );
    //             const body = response.data;
    //             console.log(body)
    //             setOptionsample(
    //                 body.map(({ genericName, id }) => ({ title: genericName, value: id }))
    //             );
    //         } catch (error) {
    //         }
    //     }
    //     getCharacters();
    // }, []);

    const handleDispense = (e) => {
        e.preventDefault()
        const date_dispensed = moment(formValues.dateDispensed).format(
            "DD-MM-YYYY"
        );
        formData.data.brand_name_dispensed = formValues.brandName
        formData.data.quantity_dispensed = formValues.qtyDispensed
        formData.data.prescription_status = 1
        formData.data.date_dispensed = date_dispensed
        formData.data.comment = formValues.comment
        const data = { ...formData };
        //props.updatePrescriptionStatus(formData.id, data);

        toggle()
    };



    return (
        <div>
            <Card>
                <CardBody>
                    <ToastContainer autoClose={3000} hideProgressBar />
                    <Modal
                        isOpen={modal}
                        toggle={toggle}
                        className={className}
                        size="xl"
                    >
                        <ModalHeader toggle={toggle} close={closeBtn}>
                            REGIMEN PRESCRIPTION
                        </ModalHeader>
                        <ModalBody>
                        <Row >
                                            
                            <Col md={4}>
                                <FormGroup>
                                    <Label for='maritalStatus'>Precription Date</Label>
                                    <DateTimePicker
                                        time={false}
                                        name="date_sample_collected"
                                        id="date_sample_collected"
                                        value={samples.date_sample_collected}
                                        
                                    />
                                    {errors.date_sample_collected !="" ? (
                                        <span className={classes.error}>{errors.date_sample_collected}</span>
                                    ) : "" }
                                </FormGroup>
                            </Col>
                            <br/><br/>
                            <Col md={8}></Col>
                            
                            <hr/>
                                <br/> 
                                <h5>Drugs Information</h5>          
                            <Col md={5}>
                                <FormGroup>
                                    <Label for='maritalStatus'>Test Order Time </Label>

                                    <DateTimePicker
                                        date={false}
                                        name="time_sample_collected"
                                        id="time_sample_collected"

                                        
                                        required
                                    />
                                    {errors.time_sample_collected !="" ? (
                                        <span className={classes.error}>{errors.time_sample_collected}</span>
                                    ) : "" }
                                </FormGroup>
                            </Col>
                            <Col md={2}></Col>
                            <Col md={5}>
                                <FormGroup>
                                    <Label for="maritalStatus">Test Order Type</Label>
                                    <Autocomplete
                                        multiple="true"
                                        id="sample_type"
                                        size="small"
                                        options={optionsample}
                                        getOptionLabel={(option) => option.title}
                                        
                                        renderTags={(value, getTagProps) =>
                                            value.map((option, index) => (
                                                <Chip
                                                    label={option.title}
                                                    {...getTagProps({ index })}
                                                    disabled={index === 0}
                                                />
                                            ))
                                        }
                                        style={{ width: "auto", marginTop: "-1rem" }}
                                        s
                                        renderInput={(params) => (
                                            <TextField
                                                {...params}
                                                variant="outlined"
                                                margin="normal"
                                            />
                                        )}
                                        required
                                    />
                                    {errors.sample_type != "" ? (
                                        <span className={classes.error}>
                                    {errors.sample_type}
                                    </span>
                                    ) : (
                                        ""
                                    )}
                                </FormGroup>
                            </Col>
                            <Col md={5}>
                                <FormGroup>
                                    <Label for="occupation">Priority* </Label>

                                    <Input
                                        type="select"
                                        name="sample_collected_by"
                                        id="sample_collected_by"
                                        vaule={otherfields.sample_collected_by}
                                        
                                        {...(errors.sample_collected_by && {
                                            invalid: true,
                                        })}
                                    >
                                        <option value=""> </option>
                                        <option value="Dorcas"> Dorcas </option>
                                        <option value="Jeph"> Jeph </option>
                                        <option value="Debora"> Debora </option>
                                    </Input>
                                    <FormFeedback>
                                        {errors.sample_collected_by}
                                    </FormFeedback>
                                </FormGroup>
                            </Col>
                            <Col md={2}></Col>
                            <Col md={5} className='float-right mr-1'>
                                <FormGroup>
                                    <Label for="occupation">Test* </Label>

                                    <Input
                                        type="select"
                                        name="sample_collected_by"
                                        id="sample_collected_by"
                                        vaule={otherfields.sample_collected_by}
                                        
                                        {...(errors.sample_collected_by && {
                                            invalid: true,
                                        })}
                                    >
                                        <option value=""> </option>
                                        <option value="Dorcas"> Dorcas </option>
                                        <option value="Jeph"> Jeph </option>
                                        <option value="Debora"> Debora </option>
                                    </Input>
                                    <FormFeedback>
                                        {errors.sample_collected_by}
                                    </FormFeedback>
                                </FormGroup>
                            </Col>
                            
                            <Col md={5}>
                                <FormGroup>
                                    <Label for="occupation">Test Order By* </Label>

                                    <Input
                                        type="select"
                                        name="sample_collected_by"
                                        id="sample_collected_by"
                                        vaule={otherfields.sample_collected_by}
                                        
                                        {...(errors.sample_collected_by && {
                                            invalid: true,
                                        })}
                                    >
                                        <option value=""> </option>
                                        <option value="Dorcas"> Dorcas </option>
                                        <option value="Jeph"> Jeph </option>
                                        <option value="Debora"> Debora </option>
                                    </Input>
                                    <FormFeedback>
                                        {errors.sample_collected_by}
                                    </FormFeedback>
                                </FormGroup>
                            </Col>
                            
                            <br />
                            <Col md={12}>
                                {loading ? (
                                    <>
                                        <Spinner /> <p> &nbsp;&nbsp;Processing...</p>
                                    </>
                                ) : (
                                    ""
                                )}
                            </Col>
                        </Row>
                        </ModalBody>
                    </Modal>
                </CardBody>
            </Card>
        </div>
    );
}


export default DispenseModal;

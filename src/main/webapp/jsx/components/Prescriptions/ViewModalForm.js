import React, {useState, useEffect} from 'react';
import {  Modal, ModalHeader, ModalBody,
    Col,Input,
    FormGroup,
    Label,Card, CardBody
} from 'reactstrap';

import MatButton from '@material-ui/core/Button'
import { makeStyles } from '@material-ui/core/styles'
import SaveIcon from '@material-ui/icons/Save'
import CancelIcon from '@material-ui/icons/Cancel'
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "react-widgets/styles.css";
import axios from "axios";
import { Row } from "react-bootstrap";
import { Segment,  } from 'semantic-ui-react'
import { url  as baseUrl, token} from "../../../api";
import Moment from 'moment';
import momentLocalizer from 'react-widgets-moment';
import { useHistory } from 'react-router-dom';

Moment.locale('en');
//momentLocalizer();

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

const ViewDispenseModal = (props) => {
   const history = useHistory();
   const drugDetails= props && props.datasample ? props.datasample : {}
   //console.log(drugDetails)
   //console.log(props)

    const { buttonLabel, className } = props;
    const toggle = props.togglestatus
    const modal = props.modalstatus
    const closeBtn = props.close
    const classes = useStyles();
    const [saving, setSaving] = useState(false);
    const [drugDispenseObj] = useState({drugDispenses:[]})
    const [formValues, setFormValues] = useState(drugDetails);

    const handleInputChange = (e) => {
        setFormValues ({ ...formValues, [e.target.name]: e.target.value });
    }
    return (
        <div>
            <Card>
                <CardBody>
                    <ToastContainer autoClose={3000} hideProgressBar />
                    <Modal
                        isOpen={modal}
                        toggle={toggle}
                        className={className}
                        size="lg"
                    >
                        <ModalHeader toggle={toggle} close={closeBtn}>
                            Dispensing
                        </ModalHeader>
                        <ModalBody>
                            <Col lg={12}>
                                <Row>
                                    <Col xl={12} >
                                    <Segment color='teal'>
                                        <Row>
                                            <Col className="col-md-6 mb-2">
                                                <strong>Drug Name :</strong> <p>{drugDetails.drugName}</p>
                                            </Col>
                                            <Col className="col-md-6 mb-2">
                                                <strong>Date Prescribed : </strong> <p>{drugDetails.dateTimePrescribed.replace("@", " ")}</p>
                                            </Col>
                                            <Col className="col-md-6 mb-2">
                                                <strong>Dose Frequency :</strong><p>{drugDetails.dosageFrequency} daily</p>
                                            </Col>
                                            <Col className="col-md-6 mb-2">
                                                <strong>Start Date :</strong><p>{drugDetails.startDate}</p>
                                            </Col>
                                            <Col className="col-md-6 mb-1">
                                                <strong>Instruction : </strong><p>{drugDetails.comment}</p>
                                            </Col>
                                        </Row>
                                    </Segment>
                                    </Col>

                                </Row>

                            </Col>
                            <br/>
                            <form>
                            <div className="row">
                                    <div className="form-group mb-3 col-md-6">
                                        <FormGroup>
                                            <Label for="dateTimeDispensed">Date Dispensed</Label>

                                            <Input
                                                type="text"
                                                name="dateTimeDispensed"
                                                value={formValues.dateTimeDispensed === null ? "" :formValues.dateTimeDispensed.replace("@", " ")}
                                                id="dateTimeDispensed"
                                                placeholder="Date Dispensed"
                                                onChange={handleInputChange}
                                                disabled={true}
                                            />
                                        </FormGroup>
                                    </div>
                                    </div>
                                    <div className="row">
                                    <div className="form-group mb-3 col-md-5">
                                        <FormGroup>
                                            <Label for="exampleNumber">Brand name</Label>
                                            <Input
                                                type="text"
                                                name="brand"
                                                value={formValues.brand}
                                                id="brand"
                                                placeholder="brand name"
                                                onChange={handleInputChange}
                                                disabled={true}
                                            />

                                        </FormGroup>
                                    </div>
                                    <div className="form-group mb-3 col-md-3">
                                        <FormGroup>
                                            <Label>Quantity</Label>
                                            <Input
                                                type="number"
                                                name="quantity"
                                                value={formValues.dosageStrength}
                                                id="quantity"
                                                onChange={handleInputChange}
                                                disabled={true}
                                            />
                                        </FormGroup>
                                    </div>
                                    <div className="form-group mb-3 col-md-4">
                                        <FormGroup>
                                            <Label >Unit</Label>
                                            <Input
                                                type="text"
                                                name="unit"
                                                id="unit"
                                                value={formValues.dosageStrengthUnit}
                                                onChange={handleInputChange}
                                                disabled={true}>
                                            </Input>
                                        </FormGroup>
                                    </div>
                                    <div className="form-group mb-3 col-md-12">
                                        <FormGroup>
                                            <Label for="comment">Note</Label>
                                            <Input
                                                type="text"
                                                name="comment"
                                                id="comment"
                                                row="40"
                                                value={formValues.comments}
                                                style={{ minHeight: 100, fontSize: 14 }}
                                                onChange={handleInputChange}
                                                disabled={true}
                                            ></Input>
                                        </FormGroup>
                                    </div>
                                </div>

                            </form>
                        </ModalBody>
                    </Modal>
                </CardBody>
            </Card>
        </div>
    );
}


export default ViewDispenseModal;

import React, { Fragment } from "react";
// BS
import { Dropdown, Nav, Tab } from "react-bootstrap";
/// Scroll
import PerfectScrollbar from "react-perfect-scrollbar";
import { Link } from "react-router-dom";
// images

// Page titie


const RecentActivities = () => {
  return (
    <Fragment>
      {/* <Ext /> */}
     
      <div className="row">

        <div className="col-xl-4 col-xxl-6 col-lg-6">
          <div className="card">
            <div className="card-header border-0 pb-0">
              <h4 className="card-title">Drug Prescribed</h4>
            </div>
            <div className="card-body">
              <PerfectScrollbar
                style={{ height: "370px" }}
                id="DZ_W_TimeLine"
                className="widget-timeline dz-scroll height370 ps ps--active-y"
              >
                <ul className="timeline">
                  <li>
                    <div className="timeline-badge primary"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                      <span>10 Days ago</span>
                      <h6 className="mb-0">
                        Date Sample Collected{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Assay{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Result Received{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge info"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                      <span>10 Days ago</span>
                      <h6 className="mb-0">
                        Date Sample Collected{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Assay{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Result Received{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge danger"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                       <span>10 Days ago</span>
                      <h6 className="mb-0">
                        Date Sample Collected{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Assay{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Result Received{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge success"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                       <span>10 Days ago</span>
                      <h6 className="mb-0">
                        Date Sample Collected{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Assay{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Result Received{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge warning"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                       <span>10 Days ago</span>
                      <h6 className="mb-0">
                        Date Sample Collected{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Assay{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Result Received{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge dark"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                       <span>10 Days ago</span>
                      <h6 className="mb-0">
                        Date Sample Collected{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Assay{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                      <h6 className="mb-0">
                        Date Result Received{" "}<br/>
                        <strong className="text-primary">04 Nov, 2021</strong>.
                      </h6>
                    </Link>
                  </li>
                </ul>
              </PerfectScrollbar>
            </div>
          </div>
        </div>
        <div className="col-xl-6 col-xxl-6 col-lg-6">
          <div className="card">
            <div className="card-header border-0 pb-0">
              <h4 className="card-title">Drug Dispensed</h4>
            </div>
            <div className="card-body">
              <PerfectScrollbar
                style={{ height: "370px" }}
                id="DZ_W_TimeLine1"
                className="widget-timeline dz-scroll style-1 height370 ps ps--active-y"
              >
                <ul className="timeline">
                  <li>
                    <div className="timeline-badge primary"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                     <span>20 Days ago</span>
                      <h6 className="mb-0">
                      Current Regimen
                        TDF(300mg)+3TC(300mg)+DTG(50mg){" "}
                        <strong className="text-info">Current Regimen Line<br/>
                        ART First Line Adult</strong>
                      </h6>
                      <p className="mb-0">
                        Refill Date<br/>
                        08 Jan, 2022
                      </p>
                      <strong className="text-warning">
                          Next Appointment<br/>
                        05 Jul, 2022
                     </strong><br/>
                     <strong className="text-primary">
                        Refill Duration<br/>
                        180
                     </strong><br/>
                     <strong className="text-teal">
                     Refill Duration<br/>
                        180
                     </strong>
                     <strong className="mb-0">
                     IPT<br/>
                    Isoniazid Preventive Therapy (IPT)
                    </strong><br/>
                    <strong className="mb-0">
                    IPT Date<b/>
                    08 Jan, 2022
                    </strong>
                    

                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge info"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                      <span>20 Days ago</span>
                      <h6 className="mb-0">
                      Current Regimen
                        TDF(300mg)+3TC(300mg)+DTG(50mg){" "}
                        <strong className="text-info">Current Regimen Line<br/>
                        ART First Line Adult</strong>
                      </h6>
                      <p className="mb-0">
                        Refill Date<br/>
                        08 Jan, 2022
                      </p>
                      <strong className="text-warning">
                          Next Appointment<br/>
                        05 Jul, 2022
                     </strong><br/>
                     <strong className="text-primary">
                        Refill Duration<br/>
                        180
                     </strong><br/>
                     <strong className="text-teal">
                     Refill Duration<br/>
                        180
                     </strong>
                     <strong className="mb-0">
                     IPT<br/>
                    Isoniazid Preventive Therapy (IPT)
                    </strong><br/>
                    <strong className="mb-0">
                    IPT Date<b/>
                    08 Jan, 2022
                    </strong>
                    

                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge danger"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                      <span>20 Days ago</span>
                      <h6 className="mb-0">
                      Current Regimen
                        TDF(300mg)+3TC(300mg)+DTG(50mg){" "}
                        <strong className="text-info">Current Regimen Line<br/>
                        ART First Line Adult</strong>
                      </h6>
                      <p className="mb-0">
                        Refill Date<br/>
                        08 Jan, 2022
                      </p>
                      <strong className="text-warning">
                          Next Appointment<br/>
                        05 Jul, 2022
                     </strong><br/>
                     <strong className="text-primary">
                        Refill Duration<br/>
                        180
                     </strong><br/>
                     <strong className="text-teal">
                     Refill Duration<br/>
                        180
                     </strong>
                     <strong className="mb-0">
                     IPT<br/>
                    Isoniazid Preventive Therapy (IPT)
                    </strong><br/>
                    <strong className="mb-0">
                    IPT Date<b/>
                    08 Jan, 2022
                    </strong>
                    

                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge success"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                      <span>20 Days ago</span>
                      <h6 className="mb-0">
                      Current Regimen
                        TDF(300mg)+3TC(300mg)+DTG(50mg){" "}
                        <strong className="text-info">Current Regimen Line<br/>
                        ART First Line Adult</strong>
                      </h6>
                      <p className="mb-0">
                        Refill Date<br/>
                        08 Jan, 2022
                      </p>
                      <strong className="text-warning">
                          Next Appointment<br/>
                        05 Jul, 2022
                     </strong><br/>
                     <strong className="text-primary">
                        Refill Duration<br/>
                        180
                     </strong><br/>
                     <strong className="text-teal">
                     Refill Duration<br/>
                        180
                     </strong>
                     <strong className="mb-0">
                     IPT<br/>
                    Isoniazid Preventive Therapy (IPT)
                    </strong><br/>
                    <strong className="mb-0">
                    IPT Date<b/>
                    08 Jan, 2022
                    </strong>
                    

                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge warning"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                      <span>20 Days ago</span>
                      <h6 className="mb-0">
                      Current Regimen
                        TDF(300mg)+3TC(300mg)+DTG(50mg){" "}
                        <strong className="text-info">Current Regimen Line<br/>
                        ART First Line Adult</strong>
                      </h6>
                      <p className="mb-0">
                        Refill Date<br/>
                        08 Jan, 2022
                      </p>
                      <strong className="text-warning">
                          Next Appointment<br/>
                        05 Jul, 2022
                     </strong><br/>
                     <strong className="text-primary">
                        Refill Duration<br/>
                        180
                     </strong><br/>
                     <strong className="text-teal">
                     Refill Duration<br/>
                        180
                     </strong>
                     <strong className="mb-0">
                     IPT<br/>
                    Isoniazid Preventive Therapy (IPT)
                    </strong><br/>
                    <strong className="mb-0">
                    IPT Date<b/>
                    08 Jan, 2022
                    </strong>
                    

                    </Link>
                  </li>
                  <li>
                    <div className="timeline-badge dark"></div>
                    <Link
                      className="timeline-panel text-muted"
                      to="/widget-basic"
                    >
                      <span>20 Days ago</span>
                      <h6 className="mb-0">
                      Current Regimen
                        TDF(300mg)+3TC(300mg)+DTG(50mg){" "}
                        <strong className="text-info">Current Regimen Line<br/>
                        ART First Line Adult</strong>
                      </h6>
                      <p className="mb-0">
                        Refill Date<br/>
                        08 Jan, 2022
                      </p>
                      <strong className="text-warning">
                          Next Appointment<br/>
                        05 Jul, 2022
                     </strong><br/>
                     <strong className="text-primary">
                        Refill Duration<br/>
                        180
                     </strong><br/>
                     <strong className="text-teal">
                     Refill Duration<br/>
                        180
                     </strong>
                     <strong className="mb-0">
                     IPT<br/>
                    Isoniazid Preventive Therapy (IPT)
                    </strong><br/>
                    <strong className="mb-0">
                    IPT Date<b/>
                    08 Jan, 2022
                    </strong>
                    

                    </Link>
                  </li>
                </ul>
              </PerfectScrollbar>
            </div>
          </div>
        </div>
       
        
 </div>
      
    </Fragment>
  );
};

export default RecentActivities;

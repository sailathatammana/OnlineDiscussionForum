// NPM Packages
import React from "react";

import Grid2 from "./Grid2";
import Grid1 from "./Grid1";

export default function HomePage() {
  return (
    <div className="wrapper">
      <div className="container">
        <div className="row mt-4">
          <div className="col-md-6">
            <div className="card-body">
              <h4 className="card-title">SDA stater Page</h4>
              <p>
                This starter template is based on Spring, PostgreSQL, React,
                React router and Axios. Check the following links for
                documentation and guides.
              </p>
            </div>
          </div>

          <div className="col-md-6">
            <div className="row">
              <div className="col-12 strong-shadow">
                <Grid1 />
              </div>

              <div className="col-12 mt-4">
                <Grid2 />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

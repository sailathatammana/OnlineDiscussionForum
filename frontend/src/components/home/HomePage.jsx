// NPM Packages
import React from "react";

import Grid2 from "./Grid2";
import Grid1 from "./Grid1";
import image from "../../assests/image.jpg";

export default function HomePage() {
  return (
    <div className="wrapper">
      <div className="container">
        <div className="row mt-4">
          <div className="col-md-6">
          <div className="card-body">
                <h4 className="card-title">About the Forum</h4>
                <div className="home-body">
                    <p>The Forum aims to promote the most effective strategies 
                      to build trust and confidence in COVID-19 vaccines.
                      As COVID-19 immunisation continues across the world, 
                      many people are still unclear as to what vaccines mean for transmission.
                      This forum allows our members to get in touch and share their views and experiences of 
                      on COVID-19 vaccination. </p>
                    
                      <img className="brandLogo" src= {image} alt="articleImage" />
                </div>
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

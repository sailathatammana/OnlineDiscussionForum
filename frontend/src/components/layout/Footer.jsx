import React from "react";

import './Footer.css';

function Footer() {
    return(
        <footer className="contactUs">
            <ul class="contactUs-list">
                <li>
                    <a href="https://www.sda.com/privacy/explanation/">Privacy</a></li>
                <li>
                    <a href="https://www.sda.com/policies?ref=pf/">Terms</a></li>
                <li>
                    <a href="https://www.sda.com/policies/cookies/">Cookies</a></li>
            </ul>
        </footer>
    );
}

export default Footer;
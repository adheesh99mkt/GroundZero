import React from "react";
import { useNavigate } from "react-router-dom";
import "./OwnerOptions.css";

const OwnerOptions = () => {
  const navigate = useNavigate();

  const handleRegisterTurf = () => {
    navigate("/register-turf"); // Redirect to TurfRegistrationForm
  };

  const handleViewTurf = () => {
    navigate("/view-turf"); // Redirect to ViewTurf (implement this page as needed)
  };

  return (
    <div className="owner-options-container">
      <h1>Choose Options</h1>
      <div className="options-buttons">
        <button onClick={handleRegisterTurf}>Register Turf</button>
        <button onClick={handleViewTurf}>View Turf</button>
      </div>
    </div>
  );
};

export default OwnerOptions;

// src/App.jsx
import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import LoginForm from "./components/LoginForm/LoginForm";
import UserRegistrationForm from "./components/UserRegistration/UserRegistrationForm";
import OwnerOptions from "./components/OwnerDashboard/OwnerOptions";
import TurfRegistrationForm from "./components/OwnerDashboard/TurfRegistrationForm";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginForm />} />
        <Route path="/register" element={<UserRegistrationForm />} />
        <Route path="/owner-options" element={<OwnerOptions />} />
        <Route path="/register-turf" element={<TurfRegistrationForm />} />
        {/* Add this route */}
        {/* Add other routes as necessary */}
      </Routes>
    </Router>
  );
};

export default App;

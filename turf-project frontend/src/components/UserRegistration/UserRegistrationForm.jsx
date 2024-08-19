import React, { useState } from "react";
import { FaLock, FaBirthdayCake, FaUsers } from "react-icons/fa";
import { SlEnvolope } from "react-icons/sl";
import { CiUser } from "react-icons/ci";
import { MdCall } from "react-icons/md";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./UserRegistrationForm.css";

const UserRegistrationForm = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    userName: "",
    phoneNo: "",
    dob: "",
    role: "",
  });

  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleRoleSelect = (role) => {
    setFormData({ ...formData, role });
    setIsDropdownOpen(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/user/signup",
        formData
      );

      if (response.status === 201) {
        alert("Registration successful");
        navigate("/");
      } else {
        alert("Registration failed: " + response.data.message);
      }
    } catch (error) {
      console.error("There was an error!", error);
      alert("An error occurred during registration.");
    }
  };

  return (
    <div className="form-box register">
      <form onSubmit={handleSubmit}>
        <h1>Registration</h1>
        <div className="input-box">
          <input
            type="email"
            name="email"
            placeholder="Email"
            required
            value={formData.email}
            onChange={handleChange}
          />
          <SlEnvolope className="icon" />
        </div>
        <div className="input-box">
          <input
            type="password"
            name="password"
            placeholder="Password"
            required
            value={formData.password}
            onChange={handleChange}
          />
          <FaLock className="icon" />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="userName"
            placeholder="Username"
            required
            value={formData.userName}
            onChange={handleChange}
          />
          <CiUser className="icon" />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="phoneNo"
            placeholder="Phone"
            required
            value={formData.phoneNo}
            onChange={handleChange}
          />
          <MdCall className="icon" />
        </div>
        <div className="input-box">
          <input
            type="date"
            name="dob"
            placeholder="DOB"
            required
            value={formData.dob}
            onChange={handleChange}
          />
          <FaBirthdayCake className="icon" />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="role"
            placeholder="Role"
            required
            value={formData.role}
            readOnly
            onClick={() => setIsDropdownOpen(!isDropdownOpen)}
          />
          <FaUsers
            className="icon"
            onClick={() => setIsDropdownOpen(!isDropdownOpen)}
          />
          {isDropdownOpen && (
            <div className="dropdown">
              <div
                className="dropdown-item"
                onClick={() => handleRoleSelect("PLAYER")}
              >
                PLAYER
              </div>
              <div
                className="dropdown-item"
                onClick={() => handleRoleSelect("OWNER")}
              >
                OWNER
              </div>
            </div>
          )}
        </div>
        <div className="remember-forget">
          <label>
            <input type="checkbox" /> I agree to the terms & conditions
          </label>
        </div>
        <button type="submit">Register</button>
        <div className="register-link">
          <p>
            Already have an account?{" "}
            <a href="#" onClick={() => navigate("/")}>
              Login
            </a>
          </p>
        </div>
      </form>
    </div>
  );
};

export default UserRegistrationForm;

import React, { useState } from "react";
import axios from "axios";
import { FaEnvelope, FaLock } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import "./LoginForm.css";

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await axios.post("http://localhost:8080/user/signin", {
        email,
        password,
      });
      console.log(data.data); // Handle success response
      sessionStorage.setItem("user", JSON.stringify(data));
      const role = data.data.role;
      // Check user role from response
      if (role === "OWNER") {
        navigate("/owner-options"); // Redirect to OwnerOptionsPage
      } else {
        navigate("/dashboard"); // Redirect to dashboard or any other page
      }
    } catch (err) {
      console.error(err);
      setError("Invalid email or password");
    }
  };

  return (
    <div className="login-container">
      <div className="wrapper">
        <h1>Login</h1>
        <form onSubmit={handleSubmit}>
          <div className="input-box">
            <input
              type="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            <div className="icon">
              <FaEnvelope />
            </div>
          </div>
          <div className="input-box">
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <div className="icon">
              <FaLock />
            </div>
          </div>
          {error && <p className="error">{error}</p>}
          <div className="remember-forget">
            <label>
              <input type="checkbox" /> Remember me
            </label>
            <a href="#">Forgot Password?</a>
          </div>
          <button type="submit">Login</button>
          <div className="register-link">
            <p>
              Don't have an account? <a href="/register">Register here</a>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
};
export default LoginForm;

import React, { useState } from "react";
import axios from "axios";
import "./TurfRegistrationForm.css";

const TurfRegistrationForm = () => {
  const [formData, setFormData] = useState({
    turf_name: "",
    price_hr: "",
    max_capacity: "",
    games: [],
    address: {
      addrLine1: "",
      addrLine2: "",
      city: "",
      zipCode: "",
      state: "",
      country: "",
    },
  });

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name in formData.address) {
      setFormData({
        ...formData,
        address: {
          ...formData.address,
          [name]: value,
        },
      });
    } else {
      setFormData({
        ...formData,
        [name]: value,
      });
    }
  };

  const handleCheckboxChange = (e) => {
    const { value, checked } = e.target;
    setFormData((prevFormData) => {
      if (checked) {
        return {
          ...prevFormData,
          games: [...prevFormData.games, value],
        };
      } else {
        return {
          ...prevFormData,
          games: prevFormData.games.filter((game) => game !== value),
        };
      }
    });
  };

  const API_URL = "http://localhost:8080";

  const addAddress = (address) => {
    return axios.post(`${API_URL}/address`, address);
  };

  const addTurf = (ownerId, turfData) => {
    return axios.post(`${API_URL}/turf/${ownerId}`, turfData);
  };

  const addGame = (game, turfId) => {
    return axios.post(`${API_URL}/games`, {
      game_name: game,
      turf_id: turfId,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = sessionStorage.getItem("user");

    if (!data) {
      console.error("No user data found in session storage!");
      alert("Please log in to continue.");
      return;
    }
    let user;
    try {
      user = JSON.parse(data);
    } catch (error) {
      console.error("Error parsing user data from session storage:", error);
      alert("Failed to retrieve user information. Please log in again.");
      return;
    }

    const ownerId = user?.data?.id;

    if (!ownerId) {
      console.error("No owner ID found in parsed user data!");
      alert("Invalid owner information. Please log in again.");
      return;
    }

    try {
      // First, save the address and get the address_id
      const addressResponse = await addAddress(formData.address);
      const addressId = addressResponse.data.id;

      // Then, save the turf with the address_id
      const turfData = {
        ...formData,
        address_id: addressId,
      };

      const turfResponse = await addTurf(ownerId, turfData);
      const turfId = turfResponse.data.id;

      // Finally, save each selected game with the turf_id
      for (let game of formData.games) {
        await addGame(game, turfId);
      }

      alert("Turf registered successfully");
    } catch (error) {
      console.error("Error registering turf:", error);
      alert("Failed to register turf");
    }
  };

  return (
    <div className="turf-registration-container">
      <h1>Register Turf</h1>
      <form onSubmit={handleSubmit}>
        <div className="input-box">
          <input
            type="text"
            name="turf_name"
            placeholder="Turf Name"
            value={formData.turf_name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="max_capacity"
            placeholder="Maximum Capacity"
            value={formData.max_capacity}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="price_hr"
            placeholder="Price"
            value={formData.price_hr}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          Games
          <div className="games-checkboxes">
            <label>
              <input
                type="checkbox"
                value="Football"
                name="games"
                onChange={handleCheckboxChange}
              />
              Football
            </label>
            <label>
              <input
                type="checkbox"
                value="Cricket"
                name="games"
                onChange={handleCheckboxChange}
              />
              Cricket
            </label>
            <label>
              <input
                type="checkbox"
                value="Hockey"
                name="games"
                onChange={handleCheckboxChange}
              />
              Hockey
            </label>
            <label>
              <input
                type="checkbox"
                value="Lacrosse"
                name="games"
                onChange={handleCheckboxChange}
              />
              Lacrosse
            </label>
          </div>
        </div>
        <div className="input-box">
          <input
            type="text"
            name="addrLine1"
            placeholder="Address Line 1"
            value={formData.address.addrLine1}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="addrLine2"
            placeholder="Address Line 2"
            value={formData.address.addrLine2}
            onChange={handleChange}
          />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="city"
            placeholder="City"
            value={formData.address.city}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="state"
            placeholder="State"
            value={formData.address.state}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="country"
            placeholder="Country"
            value={formData.address.country}
            onChange={handleChange}
            required
          />
        </div>
        <div className="input-box">
          <input
            type="text"
            name="zipCode"
            placeholder="Zip Code"
            value={formData.address.zipCode}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Register Turf</button>
      </form>
    </div>
  );
};

export default TurfRegistrationForm;

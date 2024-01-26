import React, { Component } from "react";
import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import Header from "./Header/Header";
import { ApiGet } from "../ApiConnect/ApiGet";

class Landing extends Component {
  render() {
    return (
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<ApiGet />} />
        </Routes>
      </BrowserRouter>
    );
  }
}

export default Landing;

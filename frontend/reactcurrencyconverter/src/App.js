import React from "react";
import "./App.css";
import Header from "./components/layout/Header/Header";
import { CurrencyConverterComponent } from "./components/CurrencyConverterComponent/CurrencyConverterComponent";

export default function App() {
  return (
    <div className="App">
      <Header />
      <CurrencyConverterComponent />
    </div>
  );
}

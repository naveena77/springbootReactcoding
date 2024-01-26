import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { ApiPost } from "./ApiPost";

export const ApiGet = () => {
  const [currency, setcurrency] = useState([]);

  useEffect(() => {
    getList();
  }, []);

  const url = "http://localhost:8080/api/fetch/currency";
  const options = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  function getList() {
    fetch(url, options)
      .then((data) => {
        if (data) {
          return data.json();
        } else {
          throw new Error("Something went wrong");
        }
      })
      .then((res) => {
        setcurrency(res);
        console.log(res);
      });
  }

  return (
    <div>
      <h1>Currency Converter</h1>
      <br />
      <ApiPost currency={currency}></ApiPost>
    </div>
  );
};

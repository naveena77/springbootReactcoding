import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { Button, Select } from "@mui/material";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import { styled } from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Grid";
import { CurrencyConverterService } from "../../Services/CurrencyConverterService";
import { getCurrencyLists } from "../../Services/CurrencyListsService";
const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));
export const CurrencyConverterComponent = () => {
  const [targetCurrencyInput, setTargetCurrencyInput] = useState("");
  const [currency, setCurrency] = useState([]);
  const [error, setError] = useState("");
  const [formData, setFormData] = useState({
    sourceCurrency: "EUR",
    targetCurrency: "USD",
    sourceCurrencyInput: "",
    errors: {},
  });
  useEffect(() => {
    getCurrencyLists()
      .then((res) => {
        setCurrency(res);
        console.log(res);
      })
      .catch((error) => {
        setError(error.message);
        console.log(error);
      });
  }, []);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleClear = (event) => {
    setTargetCurrencyInput("");
    setFormData({
      sourceCurrency: "EUR",
      targetCurrency: "USD",
      sourceCurrencyInput: "",
      errors: {},
    });
  };

  const validateForm = () => {
    const errors = {};

    // Check if sourceCurrency is empty
    if (!formData.sourceCurrency) {
      errors.sourceCurrency = "SourceCurrency is required";
    }

    // Check if TargetCurrency is empty
    if (!formData.targetCurrency) {
      errors.targetCurrency = "TargetCurrency is required";
    }

    // Check if Source Currency Input is empty
    if (!formData.sourceCurrencyInput) {
      errors.sourceCurrencyInput = "Source Currency Input is required";
    }

    setFormData((prevState) => ({ ...prevState, errors }));

    // Return true if there are no errors
    return Object.keys(errors).length === 0;
  };

  function handleSubmit(event) {
    event.preventDefault();
    if (validateForm()) {
      // Form is valid, submit data
      console.log(formData);
      const currencyPost = {
        sourceCurrency: formData.sourceCurrency,
        targetCurrency: formData.targetCurrency,
        enteredAmountValue: formData.sourceCurrencyInput,
      };
      console.log(currencyPost);
      CurrencyConverterService.currencyConverter(currencyPost)
        .then((res) => {
          if (res.ok) {
            return res.json();
          } else {
            throw new Error("Bad Http Request");
          }
        })
        .then((data) => {
          setTargetCurrencyInput(data);
          console.log(data);
        })
        .catch((error) => {
          alert(error);
          console.log("ERROR", error);
        });
    } else {
      return "error";
    }
  }

  return (
    <div>
      <h1>Currency Converter</h1>
      <br />
      <Box
        sx={{ flexGrow: 1 }}
        component="form"
        onSubmit={handleSubmit}
        noValidate
        autoComplete="off"
      >
        <Grid container spacing={1}>
          <Grid item xs={1}></Grid>
          <Grid item xs={10}>
            <Item>
              {error && <h3 style={{ color: "red" }}>{error}</h3>}
              <div>
                <Select
                  required
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  name="sourceCurrency"
                  value={formData.sourceCurrency}
                  label="Select"
                  onChange={handleChange}
                >
                  {currency.map((value, index) => {
                    return (
                      <MenuItem key={index} value={value.currencyIdentifier}>
                        {value.currencyIdentifier}
                      </MenuItem>
                    );
                  })}
                </Select>

                <TextField
                  required
                  id="outlined-required"
                  name="sourceCurrencyInput"
                  type="number"
                  label="Enter Source Amount"
                  value={formData.sourceCurrencyInput}
                  onChange={handleChange}
                />

                <InputLabel id="demo-simple-select-label">
                  <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                </InputLabel>
                <Select
                  required
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  name="targetCurrency"
                  value={formData.targetCurrency}
                  label="Select Source Currency"
                  onChange={handleChange}
                >
                  {currency.map((value, index) => {
                    return (
                      <MenuItem value={value.currencyIdentifier} key={index}>
                        {value.currencyIdentifier}
                      </MenuItem>
                    );
                  })}
                </Select>

                <TextField
                  id="outlined-required"
                  name="targetCurrencyInput"
                  label="Value Populates"
                  value={targetCurrencyInput}
                />
                {formData.errors.sourceCurrency && (
                  <p style={{ color: "red" }}>
                    {formData.errors.sourceCurrency}
                  </p>
                )}
                {formData.errors.sourceCurrencyInput && (
                  <p style={{ color: "red" }}>
                    {formData.errors.sourceCurrencyInput}
                  </p>
                )}
                {formData.errors.targetCurrency && (
                  <p style={{ color: "red" }}>
                    {formData.errors.targetCurrency}
                  </p>
                )}
                {setError && <p style={{ color: "red" }}>{setError}</p>}
                <Box mt={3}>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={handleClear}
                  >
                    clear
                  </Button>
                  <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                  <Button variant="contained" color="primary" type="submit">
                    Convert
                  </Button>
                </Box>
              </div>
            </Item>
          </Grid>
          <Grid item xs={1}></Grid>
        </Grid>
      </Box>
    </div>
  );
};

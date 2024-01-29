async function currencyConverter(currencyPost) {
  const url = "http://localhost:8080/api/currency/converter";

  const result = await fetch(url, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },

    //make sure to serialize your JSON body
    body: JSON.stringify(currencyPost),
  });
  return result || [];
}
export const CurrencyConverterService = {
  currencyConverter,
};

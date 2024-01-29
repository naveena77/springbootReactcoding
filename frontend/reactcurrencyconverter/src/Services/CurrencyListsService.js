export const getCurrencyLists = () => {
  const url = "http://localhost:8080/api/fetch/currency";
  const options = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  };
  return fetch(url, options).then((response) => {
    if (response.status === 200) return response.json();
    else throw new Error("Invalid Response");
  });
};

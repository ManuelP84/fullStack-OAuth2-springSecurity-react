import { useSearchParams, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import authorize from "../links/authorize";
import { Buffer } from "buffer";
import token from "../links/token";

interface IRedirectProps {}

const Redirect: React.FunctionComponent<IRedirectProps> = (props) => {
  const [searchParams, setSearchParams] = useSearchParams();
  const code = searchParams?.get("code");
  const navigate = useNavigate();

  useEffect(() => {
    if (code) {
      sessionStorage.setItem("code", code);
      const client = "client";
      const secret = "secret";
      const headers = new Headers();
      headers.set("Content-type", "application/json");
      headers.set(
        "Authorization",
        `Basic ${Buffer.from(`${client}:${secret}`).toString("base64")}`
      );

      const url = token();
      fetch(url, {
        method: "POST",
        mode: "cors",
        headers,
      })
        .then(async (response) => {
          const token = await response.json();
          if (token?.id_token) {
            sessionStorage.setItem("id_token", token.id_token);
            navigate("/home");
          }
        })
        .catch((error) => {
          console.log(error); // Normally you should use a component from a library to show the user the is something wrong or that he is not authorized
        });
    } else if (!code) {
      window.location.href = authorize();
    }
  }, []);

  return <p>Redirecting...{code}</p>;
};

export default Redirect;

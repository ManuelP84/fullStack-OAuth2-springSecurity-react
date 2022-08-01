import * as React from "react";
import { Link } from "react-router-dom";
import { generateCodeVerifier, generateCodeChallenge } from "../pkce/pkce";

interface ILoginProps {}

const Login: React.FunctionComponent<ILoginProps> = (props) => {
  const codeVerifier = generateCodeVerifier();
  sessionStorage.setItem("codeVerifier", codeVerifier);
  const codeChallenge = generateCodeChallenge();
  sessionStorage.setItem("codeChallenge", codeChallenge);
  return (
    <>
      <Link to={"/redirect"}>Login</Link>
    </>
  );
};

export default Login;

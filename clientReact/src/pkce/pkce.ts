import * as crypto from "crypto-js";

/**
 * Function to encode the string in a base64 url friendly type manner
 */
const base64Url = (str: any) => {
  return str
    .toString(crypto.enc.Base64)
    .replace(/=/g, "")
    .replace(/\+/g, "-")
    .replace(/\//g, "_");
};

const generateCodeVerifier = () => {
  return base64Url(
    crypto.enc.Base64.stringify(crypto.lib.WordArray.random(32))
  );
};

const generateCodeChallenge = () => {
  const codeVerifier = sessionStorage.getItem("codeVerifier");
  if (codeVerifier) {
    return base64Url(crypto.SHA256(codeVerifier));
  }
};

export { base64Url, generateCodeVerifier, generateCodeChallenge };

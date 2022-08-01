import { useEffect, useState } from "react";
import demo from "../links/demo";

interface IHomeProps {}

const Home: React.FunctionComponent<IHomeProps> = (props) => {
  const [demoStr, setDemoStr] = useState("default");
  useEffect(() => {
    //Make the http call. This is a side effect
    const token = sessionStorage.getItem("id_token");
    const headers = new Headers();
    headers.set("Content-type", "plain/text");
    headers.set("Authorization", `Bearer ${token}`);
    const url = demo();

    fetch(url, {
      method: "GET",
      mode: "cors",
      headers,
    }).then(async (response) => {
      const data = await response.text();
      setDemoStr(data);
    });
  }, []);
  return (
    <>
      <div className="header">
        <h1>Home</h1>
      </div>
      <div>
        <p>{demoStr}</p>
      </div>
    </>
  );
};

export default Home;

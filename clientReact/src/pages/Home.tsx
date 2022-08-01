import { useState } from "react";

interface IHomeProps {}

const Home: React.FunctionComponent<IHomeProps> = (props) => {
  const [demoStr, setDemoStr] = useState("default");
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

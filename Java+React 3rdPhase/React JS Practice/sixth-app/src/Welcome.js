import { useEffect, useState } from "react";

const Welcome = () => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    let data = localStorage.getItem("data");
    if (data) {
      data = JSON.parse(data);
      setUser(data); 
    }
  }, []);

  return (
    <>
      {user ? (
        <h1>Welcome {user.Id}</h1>
      ) : (
        <h1>Loading...</h1>
      )}
    </>
  );
};

export default Welcome;

import express from "express";
import morgan from "morgan";
import libroroutes from "./routes/libro.routes";
import editorialroutes from "./routes/editorial.controllers";
const app = express();
var cors = require("cors");
app.use(express.json());
app.use(cors());
app.use(morgan("dev"));

app.get("/", (req, res) => {
  res.send(
    "<h1>Bienvenidos, este es el backend</h1>"
  );
});

app.use("/libro", libroroutes);
app.use("/editorial", editorialroutes);

export default app;

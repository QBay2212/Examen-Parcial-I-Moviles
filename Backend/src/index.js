import app from "./app";
import { pool } from "./database";
app.listen(process.env.PORT || 3000);
console.log("server listen on PORT:", 3000);

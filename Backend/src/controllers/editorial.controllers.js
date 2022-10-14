import { pool } from "../database";
export const getEditorial = async (req, res) => {
  try {
    pool.query("SELECT * FROM editorial;", function (err, result) {
      console.log(result);
      try {
        return res.status(200).json(result);
      } catch (error) {
        return res.status(500).json("Error al listar editoriales");
      }
    });
  } catch (error) {
    return res.status(500).json("Error al listar editoriales");
  }
};
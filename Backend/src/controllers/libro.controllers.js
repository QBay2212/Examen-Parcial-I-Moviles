import { pool } from "../database";
export const getLibros = async (req, res) => {
  try {
    pool.query(
      "SELECT l.idlibro, l.titulo, l.paginas, l.autor, l.ideditorial,l.nombre editorial FROM libro l join editorial e on l.ideditorial=e.ideditorial;",
      function (err, result) {
        console.log(result);
        try {
          return res.status(200).json(result);
        } catch (error) {
          return res.status(500).json("Error al listar libro");
        }
      }
    );
  } catch (error) {
    return res.status(500).json("Error al listar Libreria");
  }
};
export const searchLibreria = async (req, res) => {
  try {
    const nombre = String(req.params.nombre);
    pool.query(
      "SELECT l.idlibro, l.titulo, l.paginas, l.autor, l.ideditorial,l.nombre editorial FROM libro l join editorial e on l.ideditorial=e.ideditorial where l.titulo=?;",
      [nombre],
      function (err, result) {
        console.log(result);
        try {
          return res.status(200).json(result);
        } catch (error) {
          return res.status(500).json("Error al buscar Libreria");
        }
      }
    );
  } catch (error) {
    return res.status(500).json("Error al listar Libreria");
  }
};
export const getLibro = async (req, res) => {
  try {
    const id = parseInt(req.params.id);
    pool.query(
      "SELECT l.idlibro, l.titulo, l.paginas, l.autor, l.ideditorial,l.nombre editorial FROM libro l join editorial e on l.ideditorial=e.ideditorial where l.idlibro=?",
      [id],
      function (err, result) {
        console.log(result);
        try {
          return res.status(200).json(result);
        } catch (error) {
          return res.status(500).json("Error al listar Libreria");
        }
      }
    );
  } catch (error) {
    return res.status(500).json("Error al listar Libreria");
  }
};
export const createLibreria = async (req, res) => {
  try {
    const titulo = req.body.nombre;
    const autor = req.body.marca;
    const paginas = parseInt(req.body.paginas);
    const edicion = parseFloat(req.body.edicion);
    const ideditorial = parseInt(req.body.ideditorial);
    pool.query(
      "INSERT INTO libro(titulo, autor, paginas, edicion, ideditorial) VALUES(?, ?, ?, ?, ?); ",
      [titulo, autor, paginas, edicion, ideditorial],
      function (err, result) {
        console.log(result);
        try {
          return res.status(200).json(result);
        } catch (error) {
          return res.status(500).json("Error al crear Libreria");
        }
      }
    );
  } catch (error) {
    return res.status(500).json("Error al crear Libreria");
  }
};
export const updateLibreria = async (req, res) => {
  try {
    const id = parseInt(req.params.id);
    const titulo = req.body.nombre;
    const autor = req.body.marca;
    const paginas = parseInt(req.body.paginas);
    const edicion = parseFloat(req.body.edicion);
    const ideditorial = parseInt(req.body.ideditorial);
    console.log(id, titulo, autor, paginas, edicion, ideditorial);
    pool.query(
      "UPDATE productos SET titulo=?, autor=?, paginas=?, edicion=?,  ideditorial=? WHERE idlibro=?;",
      [titulo, autor, paginas, edicion, ideditorial, id],
      function (err, result) {
        try {
          return res.status(200).json(result);
        } catch (error) {
          return res.status(500).json("Error al cargar Libreria");
        }
      }
    );
  } catch (error) {
    return res.status(500).json("Error al cargar Libreria");
  }
};
export const deleteLibreria = async (req, res) => {
  try {
    const id = parseInt(req.params.id);

    pool.query(
      "DELETE FROM libro WHERE idlibro=?; ",
      [id],
      function (err, result) {
        try {
          return res.status(200).json(result);
        } catch (error) {
          return res.status(500).json("Error al eliminar libreria");
        }
      }
    );
  } catch (error) {
    return res.status(500).json("Error al listar estudiante");
  }
};

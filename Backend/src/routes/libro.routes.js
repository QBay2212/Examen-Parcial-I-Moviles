import { Router } from "express";
import * as libro from "../controllers/libro.controllers";
const router = Router();
router.get("/", libro.getLibros);
router.post("/", libro.createLibreria);
router.get("/:id", libro.getLibro);
router.get("/search/:nombre", libro.searchLibreria);
router.put("/:id", libro.updateLibreria);
router.delete("/:id", libro.deleteLibreria);
export default router;
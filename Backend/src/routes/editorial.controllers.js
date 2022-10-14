import { Router } from "express";
import * as editorial from "../controllers/editorial.controllers";
const router = Router();
router.get("/", editorial.getEditorial);

export default router;

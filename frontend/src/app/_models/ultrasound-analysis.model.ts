import {
  NoduleContours,
  NoduleEchogenicity,
  NoduleElastography,
  NoduleShape,
  NoduleSize,
  NoduleStructure,
  NoduleVascularization,
  Thirads
} from "./enums";
import { UltrasoundImage } from "./ultrasound-image.model";

export interface UltrasoundAnalysis {
  size: NoduleSize,
  hasConglomerate: boolean,
  shape: NoduleShape,
  contours: NoduleContours,
  echogenicity: NoduleEchogenicity,
  vascularization: NoduleVascularization,
  elastography: NoduleElastography,
  autoimmuneThyroiditis: boolean,
  suspiciousLymphNodes: boolean,
  thirads: Thirads,
  structure: NoduleStructure[]
  images: UltrasoundImage[]
}

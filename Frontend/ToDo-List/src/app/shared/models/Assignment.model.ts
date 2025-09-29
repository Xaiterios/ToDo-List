import { AssignmentStatus } from "./AssignmentStatus.model";

export interface Assignment {
    id: string;
    title: string;
    assignmentStatus: AssignmentStatus;
}
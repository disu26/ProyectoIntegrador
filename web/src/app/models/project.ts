import { Risk } from './risk';

export interface Project {
    id: string;
    name: string;
    startDate: Date;
    endingDate: Date;
    labels: [string];
    emails: [string];
    description: string;
    status: string;
    risks: [Risk];
}
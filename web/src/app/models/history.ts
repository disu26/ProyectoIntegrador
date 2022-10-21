import { Time } from '@angular/common';
import { Project } from './project'

export interface History {
    id: string;
    date: Date;
    time: Time;
    project: [Project]
}
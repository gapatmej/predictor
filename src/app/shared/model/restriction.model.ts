import { ISchedule } from './schedule.model';

export const enum Day {
    MONDAY = 'Monday',
    TUESDAY = 'Tuesday',
    WEDNESDAY = 'Wednesday',
    THURSDAY = 'Thrusday',
    FRIDAY = 'Friday',
    SATURDAY = 'Saturday',
    SUNDAY = 'Sunday',
}

export interface IRestriction {
    id?: number;
    plateNumber?: number;
    day?: Day;
    schedule?: ISchedule;
}

export class Restriction implements IRestriction {
    constructor(
        public id?: number,
        public plateNumber?: number,
        public day?: Day,
        public schedule?: ISchedule,
    ) {}
}

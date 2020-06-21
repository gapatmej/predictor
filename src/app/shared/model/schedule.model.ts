export interface ISchedule {
    id?: number;
    fromHour?: number;
    fromMinute?: number;
    toHour?: number;
    toMinute?: number;
}

export class Schedule implements ISchedule {
    constructor(
        public id?: number,
        public fromHour?: number,
        public fromMinute?: number,
        public toHour?: number,
    ) {}
}

export class Transaction {
    id: number;
    merchant: string;
    terminalId: string;
    rrnTokenTrans: string;
    tranDate: Date;
    channel: number;
    lotNo: number;
    documentNo: number;
    operationStatus: number;
    tranAmount: number;
    tranTaxAmount: number;
    tranTipAmount: number;
    acctCustomer: string;
    acctCountable: string;
    processDate: Date;
    lastUpdate: Date;
}
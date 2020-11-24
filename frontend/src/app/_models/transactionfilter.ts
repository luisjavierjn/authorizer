export class TransactionFilter {
    merchant: string;
    terminalId: string;
    rrnTokenTrans: string;
    tranDateFrom: number;
    tranDateTo: number;
    lotNo: number;

    constructor(_merchant: string, _terminalId: string, _rrnTokenTrans: string, _tranDateFrom: number, _tranDateTo: number, _lotNo: number) {
        this.merchant = _merchant;
        this.terminalId = _terminalId;
        this.rrnTokenTrans = _rrnTokenTrans;
        this.tranDateFrom = _tranDateFrom;
        this.tranDateTo = _tranDateTo;
        this.lotNo = _lotNo;
    }
}
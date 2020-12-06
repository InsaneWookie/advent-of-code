const fs = require('fs');
const _ = require('lodash')

const values = fs.readFileSync('data.txt').toString().split("\n");
//0b1001100 * 8 + 0b100

//BFFBBFFRLL

//FFBFFFFRRL 611
//1101111110
//FFFBBBFRLR 612

//0b0100000 * 8 + 0b000 = 256
//  FBFFFFF         LLL

//0b1001100 * 8 + 0b011 = 611
//  BFFBBFF         LRR
//  BFFBBFF         RLL

const findSeat = () => {


    let seat = ""
    for(let i = 0; i < 128; i++){

    }

    for(let i = 0; i < 8; i++){

    }
}

const toSeat = number => {


}

const processLine = line => {
    let rowB = 0b1111111
    let colB = 0b111
    for (let row = 0; row < 7; row++) {

        if (line[row] === 'F') {
            rowB = rowB ^ (0b1000000 >> row)
        } else {
        }

    }

    for (let row = 0; row < 3; row++) {

        if (line[row+7] === 'L') {
            colB = colB ^ (0b100 >> row)
        } else {
        }

    }

    // console.log(rowB * 8 + colB)

    return rowB * 8 + colB


}

// processLine("FBFBBFFRLR")

const data = values.map((v) => {return { seat: v, number: processLine(v)}}).sort((a, b) => a.number-b.number)

console.log(JSON.stringify(data, null,2))

for (let i = 0; i < data.length -1; i++) {
    if(data[i].number !== data[i+1].number-1){
        console.log(i, data[i], data[i+1])
        // console.log(values[i])
        // console.log(values[i+1])
    }
}


console.log(processLine("BFFFBBFRRR"))
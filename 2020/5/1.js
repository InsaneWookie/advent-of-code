const fs = require('fs');
const _ = require('lodash')

const values = fs.readFileSync('data.txt').toString().split("\n");
//

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

const data = values.map(processLine)

console.log(_.max(data))


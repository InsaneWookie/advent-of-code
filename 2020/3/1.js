const fs = require('fs');

const values = fs.readFileSync('data.txt').toString().split("\n");


let x = 0, y = 0;
let treeCount = 0

console.log(values[0])
for(let y = 1; y < values.length; y++){

    let row = values[y].split('')

    x = (x + 3) % row.length;

    // if(x > row.length){
    //     x = 0
    // }


    let point = row[x]

    if(point === '#'){
        treeCount++
    }


    row[x] = '$'
    console.log(row.join(''))
}

console.log(treeCount)
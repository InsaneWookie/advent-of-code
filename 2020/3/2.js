const fs = require('fs');

const values = fs.readFileSync('data.txt').toString().split("\n");

// Right 1, down 1.
// Right 3, down 1. (This is the slope you already checked.)
// Right 5, down 1.
// Right 7, down 1.
// Right 1, down 2.



let calcTrees = (right, down) => {
    // console.log(right, down)
    // for(let i = 0; i<down; i++) {
    //     console.log(values[i])
    // }
    let x = 0;
    let treeCount = 0
    for(let y = down; y < values.length; y = y + down){

        let row = values[y].split('')

        x = (x + right) % row.length;

        // if(x > row.length){
        //     x = 0
        // }


        let point = row[x]

        if(point === '#'){
            treeCount++
            row[x] = 'X'
        } else {
            row[x] = 'O'
        }


        //
        // console.log(row.join(''))
        // console.log(values[y-1])
    }

    return treeCount
}

const treePos = [
    [1,1],
    [3,1],
    [5,1],
    [7,1],
    [1,2]
]

const out = treePos.map((v) => calcTrees(...v))
    .reduce((prev, current) => prev * current)

 console.log(out)
const fs = require('fs');

const values = fs.readFileSync('data.txt').toString().split("\n");
//
// byr (Birth Year)
// iyr (Issue Year)
// eyr (Expiration Year)
// hgt (Height)
// hcl (Hair Color)
// ecl (Eye Color)
// pid (Passport ID)
// cid (Country ID)


let passports = [];

const isValid = (passport) => {
    const requiredKeys = ['byr', 'iyr',
        'eyr',
        'hgt',
        'hcl',
        'ecl',
        'pid',
       // 'cid'
    ]

    for(let key of requiredKeys){
        if(!passport[key]){
            return false
        }

        let isValid = true;
        let value = passport[key];
        switch (key) {
            case 'byr':
                isValid = (value >= '1920' && value <= '2002')
                break
            case 'iyr':
                isValid = (value >= '2010' && value <= '2020')
                break
            case 'eyr':
                isValid = (value >= '2020' && value <= '2030')
                break
            case 'hgt':
                const height = value.match(/[0-9]*/)[0]
                if(/[0-9]*in/.test(value)){
                    isValid = height >= '59' && height <= '76'
                } else if(/[0-9]*cm/.test(value)){
                    isValid = height >= '150' && height <= '193'
                } else {
                    isValid = false
                }

                break
            case 'hcl':
                isValid = value.length === 7 && /^#[0-9a-f]*/.test(value)
                break
            case 'ecl':
                isValid = ['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth'].indexOf(value) > -1
                break;
            case 'pid':
                isValid = value.length === 9 && /^[0-9]*/.test(value)
                break

        }

        if(!isValid){
            console.log(value)
            console.log(passport)
            return false
        }
    }

    return true;
}



const loadPassports = (lines) => {

    let passport = {}
    for(let l of lines){

        if(l !== ''){
            l.split(' ').forEach((key) =>{
                const item = key.split(':')
                passport[item[0]] = item[1]
            })
        }


        if(l === ''){
            if(isValid(passport)){

                passports.push(passport)
            }
            passport = {}
        }
    }

    passports.push(passport)

    console.log(passports.length)
    // console.log(passports[0])

}


loadPassports(values)
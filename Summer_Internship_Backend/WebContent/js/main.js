const tableHeaderMapper = {
    Notes: 'Notes',
    cust_number: 'Customer No.',
    due_in_date: 'Due Date',
    invoice_id: 'Invoice Id',
    name_customer: 'Customer Name',
    predicted_payment_date: 'Predicted Payment Date',
    total_open_amount: 'Total Open Amount'
}
var pageIndex = 1;
function back_page() {
    if (pageIndex > 0) {
        pageIndex--;
    }
    fetchTableData()
}
function front_page() {
    if (pageIndex < 100) {
        pageIndex++;
    }
    fetchTableData()
}
const showHeader = (header) => {
    const headerData = header;
    const tableRef = document.getElementById("main-table");
    let tableHeader = "<thead><tr>";
    let checkBox = "<th><input type='checkbox' class='clickable' /></th>";
    tableHeader += checkBox;
    headerData.forEach(element => {
        tableHeader += "<th>" + element + "</th>"
    });
    tableHeader += "</tr></thead>";
    let tableBody="<tbody id='main-table-body'></tbody>";
    tableRef.innerHTML += tableHeader + tableBody;
}

const showBody = (data) => {
    const tableData = data;
    const tableRef = document.getElementById("main-table-body");
    tableRef.innerHTML = "";
    let tableEle = "";
    tableData.forEach(tableRow => {
        tableEle += "<tr>";
        var checkBox = "<td><input type='checkbox' class='clickable' /></td>";
        tableEle += checkBox;
        Object.entries(tableRow).forEach(tableRowEle => {
            const [key, value] = tableRowEle;
            tableEle += "<td>" + value + "</td>";
        });
        tableEle += "</tr>";
        // tableRef.innerHTML += tableEle;
    });
    //tableEle += "</tbody>";
    tableRef.innerHTML += tableEle;
}

const showOnLoad = (data, check = false) => {
    console.log('Data is: ', data);
    const headingList = Object.keys(data[0]).map((headerString) => tableHeaderMapper[headerString].toUpperCase())
    if (check) {
        showHeader(headingList);
    }
    showBody(data);
}


var fetchURL = "http://localhost:8080/H2HBABBA2694/fetch?page=";


const fetchTableData = (isFirstLoad = false) => {
    var URL = fetchURL + pageIndex;
    fetch(URL)
        .then(response => {
            return response.json();
        })
        .then(jsonResult => {
            showOnLoad(jsonResult, isFirstLoad)
        })
        .catch(error => {
            console.log(error)
        })
}
// Intial loading
(function () {
    fetchTableData(true)

})()




const add = () => {
    const cust_name = document.getElementById("cust_name-input").value;
    const cust_number = document.getElementById("cust_number-input").value;
    const invoice_id = document.getElementById("invoice_id-input").value;
    const invoice_amount = document.getElementById("invoice_amount-input").value;
    const due_date = document.getElementById("due_date-input").value;
    const predicted_date = document.getElementById("predicted_date-input").value;
    fetch("http://localhost:8080/H2HBABBA2694/add?")
}



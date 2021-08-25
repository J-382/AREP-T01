function drawData(jsonData){
    data = extractData(jsonData[Object.keys(jsonData)[0]]);
    tabulateData(data[0],jsonData[Object.keys(jsonData)[0]]);
    var myConfig = {
        type: "line",
        title: {
            text: 'Stock',
            flat: true
        }, 
        "scale-x": {
            "values": data[0],
            "zooming": true,
            "zoom-to": [5, 10]
        },
        "scroll-x": {
            "bar": {
                "background-color": "#0FCCE1",
                "alpha": 0.1
            },
            "handle": {"background-color": "rgb(53, 184, 149)"}}, 
        series : [{ values :  data[1]}]
    };

    zingchart.render({ 
        id : 'myChart', 
        data : myConfig, 
        height: 400, 
        width: 800
    });
}

function extractData(jsonData){
    axisX = [];
    axisY = [];
    for(let date of Object.keys(jsonData)){ axisX.push(date); }
    axisX.sort(function(a,b){
        dateA = new Date(a.replaceAll("-","/"));
        dateB = new Date(b.replaceAll("-","/"));
        return dateA >= dateB ? 1 : -1; 
    });
    for(let date of axisX){ axisY.push(parseInt(jsonData[date]["1. open"]))}
    return [axisX, axisY];
}

function tabulateData(dates,jsonData){
    table = document.getElementById("datatable");
    for(let date of dates){
        let tr = document.createElement('tr');
        let td = document.createElement('td');
        td.textContent = date;
        tr.appendChild(td);
        for(let att of Object.keys(jsonData[date])){
            td = document.createElement('td');
            td.textContent = jsonData[date][att];
            tr.appendChild(td);
        }
        table.appendChild(tr);
    }
}

function consult(timeSeries){
    let consult = document.cookie.split(';')[0].split('=')[1];
    let url = window.location.href.replace("stock.html","");
    fetch("http://localhost:4567/consult?symbol="+consult+"&timeSeries="+timeSeries)
        .then(data => data.json()).then(jsonRaw => drawData(jsonRaw)).catch(e => console.log(e));
}

function toggle_dataview(event){
    button = document.getElementById("toggle_button");
    table = document.getElementById("myTable");
    chart = document.getElementById("myChart");
    if(button.value.includes("data")){
        button.value = "Show chart";
        table.classList.add("visible_data");
        chart.classList.remove("visible_data");
    }else {
        button.value = "Show data";
        chart.classList.add("visible_data");
        table.classList.remove("visible_data");
    }
}

function changeTab(event){
    let tabs = document.getElementsByClassName('tab');
    for(let tab of tabs){
        tab.classList.remove("tab_active");
    }
    table = document.getElementById('datatable');
    chart = document.getElementById("myChart");
    chart.innerHTML = '';
    table.innerHTML = '';
    event.target.classList.add("tab_active");
    consult(event.target.textContent.toUpperCase());
}

document.addEventListener('DOMContentLoaded', () => {
    consult('DAILY'); 
    document.getElementById("toggle_button").addEventListener("click", toggle_dataview);
    let tabs = document.getElementsByClassName('tab');
    for(let tab of tabs){
        tab.addEventListener('click',changeTab);
    }
});
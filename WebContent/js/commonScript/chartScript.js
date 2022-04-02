google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

  
  // pie chart 
  var data1 = google.visualization.arrayToDataTable([
    ['Task', 'Hours per Day'],
    ['Work',     11],
    ['Eat',      2],
    ['Commute',  2],
    ['Watch TV', 2],
    ['Sleep',    7]
  ]);

  var options1 = {
    title: 'My Daily Activities'
  };

  var pieChart = new google.visualization.PieChart(document.getElementById('piechart'));

  pieChart.draw(data1, options1);


  // bar chart
  var data2 = google.visualization.arrayToDataTable([
    ["Element", "Density", { role: "style" } ],
    ["Copper", 8.94, "#b87333"],
    ["Silver", 10.49, "silver"],
    ["Gold", 19.30, "gold"],
    ["Platinum", 21.45, "color: #e5e4e2"]
  ]);

  var view2 = new google.visualization.DataView(data2);
  view2.setColumns([0, 1,
                   { calc: "stringify",
                     sourceColumn: 1,
                     type: "string",
                     role: "annotation" },
                   2]);

  var options2 = {
    title: "Density of Precious Metals, in g/cm^3",
    width: 400,
    height: 200,
    bar: {groupWidth: "95%"},
    legend: { position: "none" },
  };

  var chart2 = new google.visualization.BarChart(document.getElementById("barchart"));
  chart2.draw(view2, options2);
}

/*
$document.ready(function(){
  $(".data-table").each(function(_, table){
    $(table).DataTable();
  });
});
*/

$(function () {
  loadInfo();
});

function loadInfo() {
  $.ajax({
    url: ctx + "v2/getTableData",
    data: {
      ip: ip,
      port: port,
      dbName: dbName,
      userName: userName,
      password: password,
      dbKind: dbKind
    },
    success: function (data) {
      let resultCode = data.resultCode;
      if (resultCode == '000000') {
        loadTable(data.params);
      } else {
        alert(data.resultMsg);
      }
    }
  });
}

function loadTable(params) {
  let headerList = params.headerList;
  let fieldList = params.fieldList;
  let tableDetailInfo = params.tableDetailInfo;
  let tableHtml = '';
  let headerHtml = '';
  let headDiv = '<div style="width: 300px;height: 100%;float: left;position: relative;font-size: 22px;margin-top: 20px;flex: 1;overflow:auto;overflow-x: auto;"><ul>';
  let tailDiv = '</ul></div><div style="position: relative; overflow: auto;height: 100%;flex: 1;float: right;width: 1000px;">';
  let lis = '';
  for (let i = 0; i < headerList.length; i++) {
    headerHtml += '<th>' + headerList[i] + '</th>'
  }
  for (let i = 0; i < tableDetailInfo.length; i++) {
    let table = tableDetailInfo[i];
    let tabsColumn = table.tabsColumn;
    let tbodyHtml = "";
    for (let j = 0; j < tabsColumn.length; j++) {
      tbodyHtml += '<tr>';
      for (let k = 0; k < fieldList.length; k++) {
        let column = tabsColumn[j];
        let columnElement = column[fieldList[k]];
        if (columnElement == undefined || columnElement == '') {
          columnElement = "无";
        }
        tbodyHtml += '<td>' + columnElement + '</td>';
      }
      tbodyHtml += '</tr>';
    }

    lis += '<li><a href="#' + table.tableName + '">' + table.tableName + '</a></li>'

    tableHtml +=
      '<table id="' + table.tableName + '" class="table table-bordered table-hover">' +
      // '<caption style="font-weight: bolder;font-size: 30px">'+table.tableName+'('+table.tableComments+')'+'</caption>'+
      '<caption style="font-weight: bolder;font-size: 30px;text-align: center;">' + table.tableComments + '(' + table.tableName + ')' + '</caption>' +
      '<thead>' +
      '<tr>' + headerHtml + '</tr>' +
      '</thead>' +
      '<tbody>' + tbodyHtml + '</tbody>' +
      '</table>';
  }
  const htmls = headDiv + lis + tailDiv + tableHtml + '</div>';
  // console.log(htmls);
  $("#body").empty();
  $("#body").append(htmls);
}
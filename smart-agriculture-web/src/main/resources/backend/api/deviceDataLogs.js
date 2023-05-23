function getDeviceDateLogsPage(params){
  return $axios({
    url: '/device/dataLogs/page',
    method: 'get',
    params
  })
}

function deviceDateLogsExport(params){
  return $axios({
    url: '/device/dataLogs/export',
    method: 'get',
    params
  })
}




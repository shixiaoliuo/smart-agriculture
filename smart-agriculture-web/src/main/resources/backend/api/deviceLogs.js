function getDeviceLogPage(params){
  return $axios({
    url: '/device/deviceLog/page',
    method: 'get',
    params
  })
}


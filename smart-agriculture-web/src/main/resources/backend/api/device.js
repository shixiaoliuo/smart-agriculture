function getDevicePage(params){
  return $axios({
    url: '/device/device/page',
    method: 'get',
    params
  })
}

function deleteDevice(id) {
  return $axios({
    url: '/device/device',
    method: 'delete',
    params: { id }
  })
}

function editDevice(params) {
  return $axios({
    url: '/device/device',
    method: 'put',
    data: { ...params }
  })
}

function findById(id) {
  return $axios({
    url: `/device/device`,
    method: 'get',
    params: { id }
  })
}

function addDevice(params) {
  return $axios({
    url: '/device/device',
    method: 'post',
    data: { ...params }
  })
}

function updateStateById(params) {
  return $axios({
    url: '/device/device/updateStateById',
    method: 'put',
     params
  })
}



function selectDeviceOptions(facilitiesId) {
  return $axios({
    url: '/device/device/selectOptions',
    method: 'get',
    params: { facilitiesId }
  })
}
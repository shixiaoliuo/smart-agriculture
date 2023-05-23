function getDeviceTypePage(params){
  return $axios({
    url: '/device/type/page',
    method: 'get',
    params
  })
}

function deleteDeviceType(id) {
  return $axios({
    url: '/device/type',
    method: 'delete',
    params: { id }
  })
}

function editDeviceType(params) {
  return $axios({
    url: '/device/type',
    method: 'put',
    data: { ...params }
  })
}

function findById(id) {
  return $axios({
    url: `/device/type`,
    method: 'get',
    params: { id }
  })
}

function addDeviceType(params) {
  return $axios({
    url: '/device/type',
    method: 'post',
    data: { ...params }
  })
}


function selectDeviceTypeOptions() {
  return $axios({
    url: '/device/type/selectOptions',
    method: 'get',
    data: {  }
  })
}
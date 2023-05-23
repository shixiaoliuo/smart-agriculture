function getFacilitiesFacilitiesPage(params){
    return $axios({
        url: '/facilities/facilities/page',
        method: 'get',
        params
    })
}

function deleteFacilitiesFacilities(id) {
    return $axios({
        url: '/facilities/facilities',
        method: 'delete',
        params: { id }
    })
}

function editFacilitiesFacilities(params) {
    return $axios({
        url: '/facilities/facilities',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/facilities/facilities`,
        method: 'get',
        params: { id }
    })
}

function addFacilitiesFacilities(params) {
    return $axios({
        url: '/facilities/facilities',
        method: 'post',
        data: { ...params }
    })
}

function selectFacilitiesOptions() {
    return $axios({
        url: '/facilities/facilities/selectOptions',
        method: 'get',
        data: {  }
    })
}
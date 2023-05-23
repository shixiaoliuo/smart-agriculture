function getFacilitiesCategoryPage(params){
    return $axios({
        url: '/facilities/category/page',
        method: 'get',
        params
    })
}

function deleteFacilitiesCategory(id) {
    return $axios({
        url: '/facilities/category',
        method: 'delete',
        params: { id }
    })
}

function editFacilitiesCategory(params) {
    return $axios({
        url: '/facilities/category',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/facilities/category`,
        method: 'get',
        params: { id }
    })
}

function addFacilitiesCategory(params) {
    return $axios({
        url: '/facilities/category',
        method: 'post',
        data: { ...params }
    })
}

function selectFacilitiesCategoryOptions(params) {
    return $axios({
        url: '/facilities/category/options',
        method: 'get',
        data: { ...params }
    })
}
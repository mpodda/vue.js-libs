//var showEditDialog = false;

const topics = 
	Object.freeze({
		editPerson: "Edit Person", 
		viewPerson:"View Person", 
		deletePerson: "Delete Person"
	});

/*
 -------------------
 -- Default value --
 -------------------
 */

var defaultValue = Vue.component('default', {
    props: {
        value: {
            type: String,
            required : true
        }
    },
    template: "#default-template"
})


/*
 ---------
 -- Men --
 ---------
 */
var men = Vue.component('men', {
    props: {
        value: {
            type: String,
            required : false
        }
    },     
    template: '#men-template'
})
 
/*
 -----------
 -- Women --
 -----------
 */
 var women = Vue.component('women', {
    props: {
        value: {
            type: String,
            required : false
        }
    },     
    template: '#women-template'
})

/*
 ------------
 -- Active --
 ------------
 */
var active = Vue.component('active', {
    props: {
        value: {
            type: String,
            required : false
        }
    },     
    template: '#active-template'
})
 

/*
 --------------
 -- Inactive --
 --------------
 */
 var inactive = Vue.component('inactive', {
    props: {
        value: {
            type: String,
            required : false
        }
    },     
    template: '#inactive-template'
})


/*
 -----------------
 -- Nationality --
 -----------------
 */
var nationality = Vue.component('nationality', {
    props: {
        value: {
            type: Object,
            required : false
        }
    },     
    template: '#nationality-template'
})


let app = new Vue({
	el: "#app-content",
	
	methods: {
		getPersons () {
    		new Promise((resolve, reject) => {
				setTimeout(() => resolve (
			   		 axios.get('/persons').then((response) => {
			   			this.dataset = response.data;
				 	 })
				), 125);
    		});
		},
		getNationalities() {
    		new Promise((resolve, reject) => {
				setTimeout(() => resolve (
			   		 axios.get('/nationalities').then((response) => {
			   			this.nationalities = response.data;
				 	 })
				), 125);
    		});
		},
		openEditDialog(visible, p) {
			this.currentPerson = p;
			this.isEditDialogVisible = visible; 
		},
		closeEditDialog () {
			this.openEditDialog (false, null);
		}
	},
	data () {
		return {
			dataset: this.getPersons(),
			nationalities: this.getNationalities(),
			defaultTemplate: defaultValue,
			enablePaging:true,
            pg: new Paginator({
                propsData: {
                    recordsPerPage : 5
               }
            }),
			sorting: {
                sortableClass: "sortable",
                sortAscClass: "sortasc",
                sortDescClass: "sortdesc"
            },
			fieldsset:[
				{property:"id", title:"Id", sortable:true},
				{property:"name", title:"Name", sortable:true},
				{property:"nationality", title:"Nationality", sortable:true, sortField:"nationality.code"},
				//{property:"nationality.code", title:"Nationality", sortable:true, sortField:"nationality.code"},
				{property:"gender", title:"Gender", sortable:true,
					comparatorAsc : function (o1, o2) {
						if (o1.gender=="W") {
							return -1;
						}
						
						return 1;
					},
					comparatorDesc : function (o1, o2) {
						if (o1.gender=="W") {
							return 1;
						}
						
						return -1;
					}
				},
				{property:"active", title:"Active", sortable:true},
				{property:"comments", title:"Comments", sortable:false}
			],
			renderers: [
				{
					id: "gender",
	                value: function (value){
	                    switch (value){
	                        case "M" : return "Men";
	                        case "W" : return "Women";
	                       default : return "Unknown Value";
	                    }
	                },
	                component:
	                   function (value){
	                        switch (value){
	                            case "M" : return men;
	                            case "W" : return women;
	                           default : return defaultValue;
	                        }
	                }
				},
				{
					id: "active",
	                value: function (value){
	                    switch (value){
                        	case true : return "Yes";
                        	case false : return "No";
	                       default : return "Unknown Value";
	                    }
	                },
	                component:
	                   function (value){
	                        switch (value){
	                            case true : return active;
	                            case false : return inactive;
	                           default : return defaultValue;
	                        }
	                }
				},
				{
					id: "nationality",
	                value: function (value){
	                	return value;
	                },
	                component:
	                	function (value){
	                		return nationality;
	                	}
				}				
			],
			gridcomponents: [
                {id: "edit",
                    value: function (value){ 
                        return "Edit"
                   },
                   component: function (row){
                       return editPerson;
                       }
                   }				
			]
		}
	}
});
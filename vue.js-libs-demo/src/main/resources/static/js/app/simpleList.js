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


new Vue({
	el: "#app-content",

	data () {
		return {
			dataset: this.getPersons(),
			defaultTemplate: defaultValue,
			enablePaging:false,
			
			fieldsset:[
				{property:"id", title:"Id", sortable:false},
				{property:"name", title:"Name", sortable:false},
				{property:"nationality.code", title:"Nationality", sortable:false},
				{property:"gender", title:"Gender", sortable:false},
				{property:"active", title:"Active", sortable:false},
				{property:"comments", title:"Comments", sortable:false}
			]
		}
	},
	methods: {
		getPersons () {
    		new Promise((resolve, reject) => {
				setTimeout(() => resolve (
			   		 axios.get('/persons').then((response) => {
			   			this.dataset = response.data;
				 	 })
				), 125);
    		});
		}
	}
});
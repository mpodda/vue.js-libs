
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

let EditPersonDialog = Vue.component('edit-person-dialog', {
	props :{
        person: {
            type: Object,
            required: false,
        },
        nationalities: {
            type: Object,
            required: false,
        }		
	},
	methods: {
		close () {
			this.$emit ('onclose');
		},
		save () {
			this.$emit ('onsave');
		}
	},
	template: '#edit-person-dialog-template'
})

let editPerson = Vue.component('edit-person', {
     props: {
         value: {
            type: String,
            required : false
        },         
        data: {
            type: Object,
            required: false
        }
     },
    methods: {
        onEditPerson: function (){
        	app.openEditDialog(true, this.data);
        }
    },
    template: '#edit-person-component-template'
})

let deletePersonComponent = Vue.component('delete-person', {
     props: {
         value: {
            type: String,
            required : false
        },         
        data: {
            type: Object,
            required: false
        }
     },
    methods: {
        onDeletePerson: function (){
			//app.deletePerson(this.data);
			app.currentPerson = this.data;
			app.openDeletionConfirmationDialog();
        }
    },
    template: '#delete-person-component-template'
})

let app = new Vue({
	el: "#app-content",
	data () {
		return {
			currentInfoMessage: {title: "", text: ""},
			currentQuestionMessage: {title: "", text: ""},
			currentPerson: null,
			isEditDialogVisible:false,
			isInfoDialogVisible:false,
			isYesNoDialogVisible:false,
			dataset: this.getPersons(),
			nationalities: this.getNationalities(),
			defaultTemplate: defaultValue,
			enablePaging:true,
            pg: new Paginator({
                propsData: {
                    recordsPerPage : 25
               }
            }),
            sorting: {
                sortableClass: "sortable",
                sortAscClass: "sortasc",
                sortDescClass: "sortdesc"
            },            
			fieldsset:[
				{property:"id", title:"Id", sortable:true},
				{property:"name", title:"Name", sortable:false},
				{property:"nationality.code", title:"Nationality", sortable:true, sortField:"nationality.code"},
				{property:"gender", title:"Gender", sortable:false},
				{property:"active", title:"Active", sortable:false},
				{property:"edit", title:"Edit", sortable:false},
				{property:"delete", title:"", sortable:false}
			],
			gridcomponents: [
				{id: "edit",
                    value: function (value){ 
                        return "Edit"
                   },
                   component: function (row){
                       return editPerson;
                       }
                },
                {id: "delete",
                   value: function (value){ 
                       return "Delete"
                   },
                   component: function (row){
                      return deletePersonComponent;
                   }
               }
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
		},
		savePerson () {
    		return new Promise((resolve, reject) => {
    			setTimeout(() => resolve (
   			   		 axios.post('/person/save', this.currentPerson).then((response) => {
						const isEdit = this.currentPerson.id != null;

						this.closeEditDialog();
					
						if (!isEdit) {
							const savedPerson = response.data;

							//Add new record
							this.dataset.push(savedPerson);
						}

						this.openInfoDialog("Save person", "Saved successful");
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
		openDeletionConfirmationDialog() {
			this.currentQuestionMessage.title = "Delete Person";
			this.currentQuestionMessage.text = "Confirm delete  " + this.currentPerson.name;
			this.isYesNoDialogVisible = true;
		},
		openInfoDialog(t, m) {
			this.currentInfoMessage.title = t;
			this.currentInfoMessage.text = m;
			this.isInfoDialogVisible = true;
		},
		openEditDialog(visible, p) {
			this.currentPerson = p;
			this.isEditDialogVisible = visible; 
		},
		closeEditDialog () {
			this.openEditDialog (false, null);
		},
		confirmPersonDeletion () {
			this.deletePerson();
			this.isYesNoDialogVisible = false;
		},
		denialPersonDeletion () {
			this.currentPerson = null;
			this.isYesNoDialogVisible = false;
		},
		deletePerson(){
			const index = this.dataset.indexOf(this.currentPerson);
			
    		new Promise((resolve, reject) => {
    			setTimeout(() => resolve (
   			   		 	axios.post('/person/delete', this.currentPerson).then((response) => {
								   this.dataset.splice(index, 1);
								   this.currentPerson = null;
   			   		 	})
       			), 125);
			});
		},
		createPerson() {
    		new Promise((resolve, reject) => {
    			setTimeout(() => resolve (
   			   		 	axios.post('/person/create').then((response) => {
							//this.dataset.push(response.data);
   			   		 		this.openEditDialog(true, response.data);
   			   		 	})
       			), 125);
    		});			
		},
		onInfoDialogClose() {
			this.isInfoDialogVisible = false;
		}
	}
});
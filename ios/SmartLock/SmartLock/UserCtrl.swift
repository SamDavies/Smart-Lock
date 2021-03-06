//
//  User.swift
//  SmartLock
//
//  Created by Sam Davies on 02/11/2015.
//  Copyright © 2015 Sam Davies. All rights reserved.
//

import UIKit

class UserCtrl: PromiseTableFeed {
    
    var users: [User] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationController!.navigationBar.barStyle = UIBarStyle.Black
        self.navigationController!.navigationBar.translucent = false
        self.automaticallyAdjustsScrollViewInsets = false
        self.table.allowsSelection = false
    }
    
    /*
    Get the post feed for the specified thing
    */
    override func getGridObjects() {
        //        self.spinner.startAnimating()
        User.getUserList(nil).then {
            (users) -> Void in
            self.users = users
            self.reloadCells()
        }
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.users.count
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = table.dequeueReusableCellWithIdentifier("UserCell", forIndexPath: indexPath) as! UserCell
        cell.create(users[indexPath.item])
        return cell
    }
}
